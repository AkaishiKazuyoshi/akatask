import { expect, it, describe } from 'vitest';
describe('数値のテスト', () => {
  it('数値の等価性テスト', () => {
    expect(2 + 2).toBe(4);
  });

  it('数値の不等価性テスト', () => {
    expect(2 + 2).not.toBe(5);
  })
})